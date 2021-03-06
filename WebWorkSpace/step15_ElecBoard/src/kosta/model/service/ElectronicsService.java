package kosta.model.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import kosta.model.dao.ElectronicsDAO;
import kosta.model.dao.ElectronicsDAOImpl;
import kosta.model.dto.Electronics;

public class ElectronicsService {

	private static ElectronicsDAO elecDAO = new ElectronicsDAOImpl();

	/**
	 * ElectronicsDAOImpl의 모든레코드 검색하는 메소드 호출
	 */
	public static List<Electronics> selectAll() throws SQLException {
		List<Electronics> list = elecDAO.selectAll();

		return list;

	}

	/**
	 * ElectronicsDAOImpl의 레코드 삽입하는 메소드 호출
	 */
	public static int insert(Electronics e) throws SQLException {
		int result = elecDAO.insert(e);
		if (result == 0) {
			new SQLException("등록되지않았습니다.");
		} // if
		return result;
	}

	/**
	 * ElectronicsDAOImpl의 모델번호에 해당하는 레코드 검색하는 메소드 호출
	 * 
	 * @param : boolean flag - 조회수 증가 여부를 판별하는 매개변수임 (true이면 조회수증가 / false이면 조회수
	 *          증가안함)
	 */
	public static Electronics selectByModelnum(String modelNum, boolean flag) throws SQLException {
		if (flag) {
			int result = elecDAO.increamentByReadnum(modelNum);

			if (result == 0) {
				throw new SQLException("조회수 증가에 오류가 발생했습니다");
			}
		}

		Electronics e = elecDAO.selectByModelNum(modelNum);
		if (e == null) {
			throw new SQLException(modelNum + "에 해당하는 정보가 없습니다.");

		}
		return e;

	}

	// 글번호에 해당하는 게시물 검색

	/**
	 * ElectronicsDAOImpl의 모델번호에 해당하는 레코드 삭제 메소드 호출
	 * @throws SQLException 
	 */

	public static int delete(String modelNum, String password, String path) throws SQLException {
		Electronics dbElec = elecDAO.selectByModelNum(modelNum);
		if(!dbElec.getPassword().equals(password)) {
			throw new SQLException("비밀번호 오류입니다");
			
		}
		int result = elecDAO.delete(modelNum, password);
		if (result == 0) {
			throw new SQLException("삭제되지 않았습니다");
			
		}
		if(dbElec.getFname() != null) {
			new File(path, dbElec.getFname()).delete();
		}
		
		return result;
		
	}

	/**
	 * ElectronicsDAOImpl의 모델번호에 해당하는 레코드 수정 메소드 호출
	 */
	public static int update(Electronics electronics) throws SQLException {

		Electronics dbElec = elecDAO.selectByModelNum(electronics.getModelNum());

		// 수정하기 전에 비밀번호 먼저 체크한다
		if (!dbElec.getPassword().equals(electronics.getPassword())) {
			throw new SQLException("비밀번호를 다시 확인해주세요");
		}
		int result = elecDAO.update(electronics);
		if (result == 0) {
			throw new SQLException("수정되지 않았습니다");
			
		}return result;
	}

}
