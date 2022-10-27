package com.kjh.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjh.exam.demo.service.ArticleService;
import com.kjh.exam.demo.service.BoardService;
import com.kjh.exam.demo.service.ReactionPointService;
import com.kjh.exam.demo.util.Ut;
import com.kjh.exam.demo.vo.Article;
import com.kjh.exam.demo.vo.Board;
import com.kjh.exam.demo.vo.ResultData;
import com.kjh.exam.demo.vo.Rq;

@Controller
public class UsrReactionPointController {
		
	@Autowired
	private ReactionPointService reactionPointService;
	@Autowired
	private Rq rq;

	@RequestMapping("/usr/reactionPoint/doGoodReaction")
	@ResponseBody
	public String doGoodReaction(String relTypeCode , int relId, String replaceUri) {
		boolean actorCanMakeReaction = reactionPointService.actorCanMakeReaction(rq.getLoginedMemberId(),relTypeCode,relId );
		if(actorCanMakeReaction==false) {
			return rq.jsHistoryBack("이미 처리되었습니다.");
		}
		reactionPointService.addGoodReactionPoint(rq.getLoginedMemberId(),relTypeCode,relId);
		
		return rq.jsReplace("좋아요!", replaceUri);
	}
	
	@RequestMapping("/usr/reactionPoint/doBadReaction")
	@ResponseBody
	public String doBadReaction(String relTypeCode , int relId, String replaceUri) {
		boolean actorCanMakeReaction = reactionPointService.actorCanMakeReaction(rq.getLoginedMemberId(),relTypeCode,relId );
		if(actorCanMakeReaction==false) {
			return rq.jsHistoryBack("이미 처리되었습니다.");
		}
		
		reactionPointService.addBadReactionPoint(rq.getLoginedMemberId(),relTypeCode,relId);
		
		return rq.jsReplace("싫어요!", replaceUri);
	}

}