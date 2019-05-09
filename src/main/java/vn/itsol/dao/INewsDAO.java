package vn.itsol.dao;

import java.util.List;

import vn.itsol.model.NewsModel;

public interface INewsDAO {

	List<NewsModel> findAll();

	Integer insertNews(NewsModel newsM);

	NewsModel findById(Integer id);

	void updateNews(NewsModel newsM);

	boolean hideNews(NewsModel newsM);

	boolean deleteNews(NewsModel newsM);
}
