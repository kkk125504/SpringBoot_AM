package com.kjh.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjh.exam.demo.repository.ReactionPointRepository;
import com.kjh.exam.demo.vo.ResultData;

@Service
public class ReactionPointService {

	@Autowired
	private ReactionPointRepository reactionPointRepository;
	@Autowired
	private ArticleService articleService;

	public boolean actorCanMakeReaction(int actorId, String relTypeCode, int id) {
		// 로그인 안한상태
		if (actorId == -1) {
			return false;
		}
		return reactionPointRepository.getSumReactionPointByMemberId(actorId, relTypeCode, id) == 0;
	}

	public ResultData addGoodReactionPoint(int actorId, String relTypeCode, int relId) {
		reactionPointRepository.addGoodReactionPoint(actorId,relTypeCode,relId);
			
		switch (relTypeCode) {
			case "article":
			articleService.increaseGoodReactionPoint(relId);
			break;
		}
		return ResultData.from("S-1", "좋아요 처리 되었습니다");
	}

	public ResultData addBadReactionPoint(int actorId, String relTypeCode, int relId) {
		reactionPointRepository.addBadReactionPoint(actorId,relTypeCode,relId);
		switch (relTypeCode) {
		case "article":
		articleService.increaseBadReactionPoint(relId);
		break;
	}
		articleService.increaseBadReactionPoint(relId);
		return ResultData.from("F-1", "싫어요 처리 되었습니다");
	}
}
