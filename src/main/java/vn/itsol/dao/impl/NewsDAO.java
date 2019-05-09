package vn.itsol.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.itsol.dao.INewsDAO;
import vn.itsol.model.NewsModel;
import vn.itsol.util.ConnectionDB;

public class NewsDAO implements INewsDAO {

	@Override
	public List<NewsModel> findAll() {
		List<NewsModel> listNews = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConnectionDB.openConnection();
			String sql = "SELECT*FROM NEWS WHERE STATUS = 1 AND CHECKED = 1";
			pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				NewsModel newsM = new NewsModel();
				newsM.setNewsId(rs.getInt("newsid"));
				newsM.setTitle(rs.getString("title"));
				newsM.setCatalogi(rs.getString("catalogi"));
				newsM.setContent(rs.getString("content"));
				newsM.setPostDate(rs.getString("postdate"));
				newsM.setCreateDate(rs.getString("createdate"));
				newsM.setEditDate(rs.getString("editdate"));
				newsM.setDeleteDate(rs.getString("deletedate"));
				newsM.setLinks(rs.getString("links"));
				newsM.setEmail(rs.getString("email"));
				newsM.setPhoneNumber(rs.getString("phonenumber"));
				newsM.setChecked(rs.getInt("checked"));
				newsM.setStatus(rs.getInt("status"));
				newsM.setUserId(rs.getInt("userid"));
				listNews.add(newsM);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn, pstm);
		}

		return listNews;
	}

	@SuppressWarnings("resource")
	@Override
	public Integer insertNews(NewsModel newsM) {

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Integer id = 0;
		try {

			conn = ConnectionDB.openConnection();
			String sql = "INSERT INTO news(title,links,catalogi,content,postdate,createdate,editdate,deletedate,email,phonenumber,checked,status,userid) values(?,?,?,?,TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'),?,?,?,?,?,?,?,?)";
			String listid[] = { "NEWSID" };
			pstm = conn.prepareStatement(sql, listid);

			pstm.setString(1, newsM.getTitle());
			pstm.setString(2, newsM.getLinks());
			pstm.setString(3, newsM.getCatalogi());
			pstm.setString(4, newsM.getContent());
			pstm.setString(5, newsM.getPostDate());
			pstm.setString(6, newsM.getCreateDate());
			pstm.setString(7, newsM.getEditDate());
			pstm.setString(8, newsM.getDeleteDate());
			pstm.setString(9, newsM.getEmail());
			pstm.setString(10, newsM.getPhoneNumber());
			pstm.setInt(11, newsM.getChecked());
			pstm.setInt(12, newsM.getStatus());
			pstm.setInt(13, newsM.getUserId());
			pstm.executeUpdate();
			rs = pstm.getGeneratedKeys();
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn, pstm);
		}

		return id;
	}

	@Override
	public NewsModel findById(Integer id) {

		Connection conn = null;
		PreparedStatement pstm = null;
		NewsModel newsM = new NewsModel();
		try {

			conn = ConnectionDB.openConnection();
			String sql = "SELECT*FROM NEWS WHERE STATUS = 1 AND CHECKED = 1 and NEWSID = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				newsM.setNewsId(rs.getInt("newsid"));
				newsM.setTitle(rs.getString("title"));
				newsM.setCatalogi(rs.getString("catalogi"));
				newsM.setContent(rs.getString("content"));
				newsM.setPostDate(rs.getString("postdate"));
				newsM.setCreateDate(rs.getString("createdate"));
				newsM.setEditDate(rs.getString("editdate"));
				newsM.setDeleteDate(rs.getString("deletedate"));
				newsM.setLinks(rs.getString("links"));
				newsM.setEmail(rs.getString("email"));
				newsM.setPhoneNumber(rs.getString("phonenumber"));
				newsM.setChecked(rs.getInt("checked"));
				newsM.setStatus(rs.getInt("status"));
				newsM.setUserId(rs.getInt("userid"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn, pstm);
		}

		return newsM;
	}

	@Override
	public void updateNews(NewsModel newsM) {
		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			conn = ConnectionDB.openConnection();
			String sql = "UPDATE news SET title = ?,links = ?,catalogi = ?,content = ?,postdate = TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'),createdate = ?,editdate = ?,deletedate = ?,email = ?,phonenumber = ?,checked = ?,status = ?,userid = ? WHERE newsid = ?";
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, newsM.getTitle());
			pstm.setString(2, newsM.getLinks());
			pstm.setString(3, newsM.getCatalogi());
			pstm.setString(4, newsM.getContent());
			pstm.setString(5, newsM.getPostDate());
			pstm.setString(6, newsM.getCreateDate());
			pstm.setString(7, newsM.getEditDate());
			pstm.setString(8, newsM.getDeleteDate());
			pstm.setString(9, newsM.getEmail());
			pstm.setString(10, newsM.getPhoneNumber());
			pstm.setInt(11, newsM.getChecked());
			pstm.setInt(12, newsM.getStatus());
			pstm.setInt(13, newsM.getUserId());
			pstm.setInt(14, newsM.getNewsId());
			pstm.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn, pstm);
		}

	}

	@Override
	public boolean deleteNews(NewsModel newsM) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean check = true;

		try {

			conn = ConnectionDB.openConnection();
			String sql = "DELETE FROM news WHERE newsid = ?";
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, newsM.getNewsId());
			pstm.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			check = false;
		} finally {
			ConnectionDB.closeConnection(conn, pstm);
		}
		return check;
	}

	@Override
	public boolean hideNews(NewsModel newsM) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean check = true;
		try {

			conn = ConnectionDB.openConnection();
			String sql = "UPDATE news SET status = ? WHERE newsid = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, newsM.getStatus());
			pstm.setInt(2, newsM.getNewsId());
			pstm.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			check = false;
		} finally {
			ConnectionDB.closeConnection(conn, pstm);
		}
		return check;

	}
}
