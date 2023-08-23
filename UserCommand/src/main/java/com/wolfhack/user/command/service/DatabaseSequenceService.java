package com.wolfhack.user.command.service;

import com.wolfhack.user.command.model.entity.DatabaseSequence;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@RequiredArgsConstructor
public class DatabaseSequenceService {

	private final MongoOperations mongoOperations;

	public long generateSequence(String seqName) {
		Query query = query(where("_id").is(seqName));
		Update sequenceUpdate = new Update().inc("sequence", 1);
		FindAndModifyOptions options = options().returnNew(true).upsert(true);

		DatabaseSequence counter = mongoOperations.findAndModify(query, sequenceUpdate, options, DatabaseSequence.class);
		return !Objects.isNull(counter) ? counter.getSequence() : 1;
	}

}
