package vn.itsol.service.impl;

import java.util.List;

import vn.itsol.dao.INewsDAO;
import vn.itsol.dao.impl.NewsDAO;
import vn.itsol.model.NewsModel;
import vn.itsol.service.INewsService;



public class NewsService implements INewsService {

	INewsDAO newsDAO = new NewsDAO();

	@Override
	public List<NewsModel> findAll() {

		return newsDAO.findAll();
	}

	@Override
	public Integer insertNews(NewsModel newsM) {
		// TODO Auto-generated method stub
		return newsDAO.insertNews(newsM);
	}

	@Override
	public NewsModel findById(Integer id) {
		// TODO Auto-generated method stub
		return newsDAO.findById(id);
	}

	@Override
	public void updateNews(NewsModel newsM) {
		newsDAO.updateNews(newsM);
	}

	@Override
	public boolean deleteNews(NewsModel newsM) {
		// TODO Auto-generated method stub
		return newsDAO.deleteNews(newsM);
	}

	@Override
	public boolean hideNews(NewsModel newsM) {
		return newsDAO.hideNews(newsM);

	}

}
