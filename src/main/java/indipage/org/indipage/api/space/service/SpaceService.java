package indipage.org.indipage.api.space.service;

import indipage.org.indipage.api.article.service.ArticleService;
import indipage.org.indipage.api.space.controller.dto.response.BookRecommendationResponseDto;
import indipage.org.indipage.api.space.controller.dto.response.FollowSpaceRelationResponseDto;
import indipage.org.indipage.api.space.controller.dto.response.SpaceDto;
import indipage.org.indipage.api.space.controller.dto.response.SpaceOfArticleResponseDto;
import indipage.org.indipage.api.ticket.service.TicketService;
import indipage.org.indipage.api.user.service.UserService;
import indipage.org.indipage.domain.*;
import indipage.org.indipage.domain.Relation.BookRecommendationRelation;
import indipage.org.indipage.domain.Relation.FollowSpaceRelation;
import indipage.org.indipage.domain.Relation.FollowSpaceRelationId;
import indipage.org.indipage.domain.Relation.SpaceTagRelation;
import indipage.org.indipage.exception.Error;
import indipage.org.indipage.exception.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpaceService {

    private final SpaceRepository spaceRepository;
    private final FollowSpaceRelationRepository followSpaceRelationRepository;
    private final UserService userService;
    private final ArticleService articleService;
    private final TicketService ticketService;

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

    public SpaceOfArticleResponseDto readArticleOfSpace(final Long spaceId) {
        Space space = findSpace(spaceId);
        Article article = articleService.findArticleBySpace(space);
        return SpaceOfArticleResponseDto.of(space, article);
    }

    public Space findSpace(final Long spaceId) {
        return spaceRepository.findById(spaceId).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_SPACE_EXCEPTION,
                        Error.NOT_FOUND_SPACE_EXCEPTION.getMessage()));
    }

    private boolean isFollowSpace(final Long userId, final Long spaceId) {
        return followSpaceRelationRepository.findFollowSpaceRelationByFollowSpaceRelationId(new FollowSpaceRelationId(userId, spaceId)).isPresent();
    }
}
