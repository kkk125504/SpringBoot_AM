package com.kjh.exam.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjh.exam.demo.repository.ArticleRepository;
import com.kjh.exam.demo.util.Ut;
import com.kjh.exam.demo.vo.Article;
import com.kjh.exam.demo.vo.ResultData;

@Service
public class ArticleService {
	// 인스턴스 변수
	private ArticleRepository articleRepository;

	@Autowired
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public Article getForPrintArticle(int actorId, int id) {
		Article article = articleRepository.getForPrintArticle(id);
		updateForPrintData(actorId,article);
		return article;
	}

	public List<Article> getForPrintArticles(int actorId, int boardId) {
		List<Article> articles = articleRepository.getForPrintArticles(boardId);
		for(Article article: articles) {
			updateForPrintData(actorId,article);
		}
		 return articles;
	}

	private void updateForPrintData(int actorId, Article article) {
		if (article == null) {
			return;
		}
		ResultData actorCanDeleteRd = actorCanDelete(actorId,article);
		article.setExtra__actorCanDelete(actorCanDeleteRd.isSuccess());
		ResultData actorCanModify = actorCanModify(actorId,article);
		article.setExtra__actorCanModify(actorCanModify.isSuccess());
	}

	public ResultData<Integer> writeArticle(int loginedMemberId, String title, String body) {
		articleRepository.writeArticle(loginedMemberId, title, body);
		int id = articleRepository.getLastInsertId();

		return ResultData.from("S-1", Ut.f("%d번 게시물이 생성되었습니다.", id),"id", id);
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public ResultData<Article> modifyArticle(int id, String title, String body) {

		articleRepository.modifyArticle(id, title, body);
		Article article = getForPrintArticle(0,id);
		return ResultData.from("S-1", Ut.f("%d번 게시물 수정 했습니다.", id), "article", article);
	}

	public ResultData actorCanModify(int loginedMemberId, Article article) {		
		if (loginedMemberId != article.getMemberId()) {
			return ResultData.from("F-2", "해당 게시물에 대한 수정 권한이 없습니다.");
		}
		return ResultData.from("S-1", "수정 가능 합니다.");
	}
	
	public ResultData actorCanDelete(int actorId, Article article) {
		
		if(article ==null) {
			return ResultData.from("F-1", "해당 게시물이 존재하지 않습니다");
		}
		
		if (actorId != article.getMemberId()) {
			return ResultData.from("F-2", "해당 게시물에 대한 수정 권한이 없습니다.");
		}
		return ResultData.from("S-1", "삭제 가능 합니다.");
	}

}
