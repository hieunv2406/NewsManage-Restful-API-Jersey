package vn.itsol.service;

import java.util.List;

import vn.itsol.model.NewsModel;

public interface INewsService {
	List<NewsModel> findAll();

	Integer insertNews(NewsModel newsM);

	NewsModel findById(Integer id);

	void updateNews(NewsModel newsM);

	boolean deleteNews(NewsModel newsM);

	boolean hideNews(NewsModel newsM);
}

