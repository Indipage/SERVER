package indipage.org.indipage.api.space.service;

import indipage.org.indipage.api.article.service.ArticleService;
import indipage.org.indipage.api.space.controller.dto.response.*;
import indipage.org.indipage.api.user.service.UserService;
import indipage.org.indipage.domain.*;
import indipage.org.indipage.domain.Relation.*;
import indipage.org.indipage.exception.Error;
import indipage.org.indipage.exception.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpaceService {

    private final SpaceRepository spaceRepository;
    private final FollowSpaceRelationRepository followSpaceRelationRepository;
    private final InviteSpaceRelationRepository inviteSpaceRelationRepository;
    private final UserService userService;
    private final ArticleService articleService;

    public SpaceDto readSpace(final Long spaceId) {
        Space space = findSpace(spaceId);

        List<SpaceTagRelation> spaceTagRelations = space.getSpaceTagRelations();
        List<Tag> spaceTags = spaceTagRelations.stream().map(SpaceTagRelation::getTag).collect(Collectors.toList());

        return SpaceDto.of(space, spaceTags);
    }

    public List<BookRecommendationResponseDto> readBookRecommendation(final Long spaceId) {
        Space space = findSpace(spaceId);

        List<BookRecommendationRelation> bookRecommendationRelations = space.getBookRecommendationRelations();

        List<BookRecommendationResponseDto> responseDtos = new ArrayList<>();
        for (BookRecommendationRelation relation : bookRecommendationRelations
        ) {
            responseDtos.add(BookRecommendationResponseDto.of(relation.getBook(), relation.getComment()));
        }

        return responseDtos;
    }

    @Transactional(rollbackOn = Exception.class)
    public void visit(final Long userId, final Long spaceId) {

        User user = userService.findUser(userId);
        Space space = findSpace(spaceId);

        InviteSpaceRelation relation = inviteSpaceRelationRepository.findByInviteSpaceRelationId(
                InviteSpaceRelationId.newInstance(user, space)).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_TICKET_RECEIVE_EXCEPTION,
                        Error.NOT_FOUND_TICKET_RECEIVE_EXCEPTION.getMessage()));

        // 방문하기
        relation.visit();
        inviteSpaceRelationRepository.save(relation);
    }

    public FollowSpaceRelationResponseDto readFollowSpace(final Long userId, final Long spaceId) {
        Space space = findSpace(spaceId);
        User user = userService.findUser(userId);
        return FollowSpaceRelationResponseDto.of(isFollowSpace(userId, spaceId));
    }

    public void createFollowSpace(final Long userId, final Long spaceId) {
        User user = userService.findUser(userId);
        Space space = findSpace(spaceId);
        followSpaceRelationRepository.save(FollowSpaceRelation.create(user, space));
    }

    public ArticleOfSpaceResponseDto readArticleOfSpace(final Long spaceId) {
        Space space = findSpace(spaceId);
        Article article = articleService.findArticleBySpace(space);
        return ArticleOfSpaceResponseDto.of(space, article);
    }

    public Space findSpace(final Long spaceId) {
        return spaceRepository.findById(spaceId).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_SPACE_EXCEPTION,
                        Error.NOT_FOUND_SPACE_EXCEPTION.getMessage()));
    }

    public List<SpaceSearchWithCategoryResponseDto> searchSpace(String searchWord) {

        List<Space> spaces = findResultOfSearch(searchWord);

        HashMap<String, List<SpaceSearchResponseDto>> resultMap = new HashMap<>();

        for (Space space : spaces) {
            String categoryName = getAddressCategoryName(space);

            List<SpaceSearchResponseDto> resultList = resultMap.computeIfAbsent(categoryName, k -> new ArrayList<>());
            resultList.add(SpaceSearchResponseDto.of(space));
        }

        List<SpaceSearchWithCategoryResponseDto> result = new ArrayList<>();

        for (String key : resultMap.keySet()) {
            result.add(SpaceSearchWithCategoryResponseDto.of(key, resultMap.get(key)));
        }

        return result;
    }

    private List<Space> findResultOfSearch(String searchWord) {
        if (Objects.equals(searchWord, "")) {
            return spaceRepository.findAll();
        }
        return spaceRepository.searchByAddress(searchWord);
    }

    private String getAddressCategoryName(Space space) {
        String metroGovernment = space.getAddress().getMetroGovernment();
        if (metroGovernment.equals("제주") || metroGovernment.equals("세종")) {
            return metroGovernment;
        }
        return space.getAddress().getBaseGovernment();
    }

    private boolean isFollowSpace(final Long userId, final Long spaceId) {
        return followSpaceRelationRepository.findFollowSpaceRelationByFollowSpaceRelationId(new FollowSpaceRelationId(userId, spaceId)).isPresent();
    }
}
