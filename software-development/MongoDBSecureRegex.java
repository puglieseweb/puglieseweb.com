import com.scw.sports.db.mongo.model.Competition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.MongoRegexCreator;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomCompetitionRepositoryImpl
        implements CustomCompetitionRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Competition> findByNameContains(
            String searchStr,
            Pageable pageable
    ) {
        String regexStr = MongoRegexCreator.INSTANCE.toRegularExpression(
                searchStr,
                MongoRegexCreator.MatchMode.CONTAINING
        );
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(regexStr));
        long total = mongoTemplate.count(query, Competition.class);

        query = query.with(pageable);
        List<Competition> founded
                = mongoTemplate.find(query, Competition.class);

        return new PageImpl<>(founded, pageable, total);
    }
}
