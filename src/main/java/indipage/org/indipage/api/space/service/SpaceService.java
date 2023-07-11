package indipage.org.indipage.api.space.service;

import indipage.org.indipage.api.space.controller.dto.response.BookRecommendationResponseDto;
import indipage.org.indipage.api.space.controller.dto.response.SpaceDto;
import indipage.org.indipage.domain.Relation.BookRecommendationRelation;
import indipage.org.indipage.domain.Relation.SpaceTagRelation;
import indipage.org.indipage.domain.Space;
import indipage.org.indipage.domain.SpaceRepository;
import indipage.org.indipage.domain.Tag;
import indipage.org.indipage.exception.Error;
import indipage.org.indipage.exception.model.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpaceService {

    private final SpaceRepository spaceRepository;

    public SpaceDto readSpace(final Long spaceId) {
        Space space = spaceRepository.findById(spaceId).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_SPACE_EXCEPTION,
                        Error.NOT_FOUND_SPACE_EXCEPTION.getMessage()));

        List<SpaceTagRelation> spaceTagRelations = space.getSpaceTagRelations();
        List<Tag> spaceTags = spaceTagRelations.stream().map(SpaceTagRelation::getTag).collect(Collectors.toList());

        return SpaceDto.of(space, spaceTags);
    }

    public List<BookRecommendationResponseDto> readBookRecommendation(final Long spaceId) {
        Space space = spaceRepository.findById(spaceId).orElseThrow(
                () -> new NotFoundException(Error.NOT_FOUND_SPACE_EXCEPTION,
                        Error.NOT_FOUND_SPACE_EXCEPTION.getMessage()));

        List<BookRecommendationRelation> bookRecommendationRelations = space.getBookRecommendationRelations();

        List<BookRecommendationResponseDto> responseDtos = new ArrayList<>();
        for (BookRecommendationRelation relation : bookRecommendationRelations
        ) {
            responseDtos.add(BookRecommendationResponseDto.of(relation.getBook(), relation.getComment()));
        }

        return responseDtos;
    }
}
