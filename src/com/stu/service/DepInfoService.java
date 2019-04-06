package com.stu.service;

import com.stu.bean.DepInfoBean;
import com.stu.bean.SpecilinfoBean;
import com.stu.dbconn.DBConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepInfoService extends DBConnection {

	//����Ժϵ��Ϣ����
	public int add(DepInfoBean depBean) {
		int result = 0;
		
		String sql = "insert into depinfo(depinfocode,depinfoname,depinfopreoftech,depinfoassteach) values(?,?,?,?)";
		
		conn = getDBConnect();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, depBean.getDepinfocode());
			pst.setString(2, depBean.getDepinfoname());
			pst.setString(3, depBean.getDepinfopreoftech());
			pst.setString(4, depBean.getDepinfoassteach());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConn();
		}
		return result;
	}
	
	
	//��ѯԺϵ��Ϣ����
	public List<DepInfoBean> select(DepInfoBean depBean){
		List<DepInfoBean> depList = new ArrayList<DepInfoBean>();
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("select d.*,(select count(*) from specilinfo p WHERE p.Depinfoid=d.Depinfoid) spnum,(select count(*) from specilinfo p,classinfo c where p.Spilinfoid=c.Spilinfoid and p.Depinfoid = d.Depinfoid) cnum from depinfo d where 1 = 1 ");
		
		if(!"".equals(depBean.getDepinfoid())&&depBean.getDepinfoid()!=null) {
			sb.append(" and depinfoid = "+depBean.getDepinfoid());
		}
		else if(!"".equals(depBean.getDepinfocode())&&depBean.getDepinfocode()!=null) {
			sb.append( "and depinfocode = '"+depBean.getDepinfocode()+"'");
		}
		else if(!"".equals(depBean.getDepinfoname())&&depBean.getDepinfoname()!=null) {
			sb.append(" and depinfoname like '%"+depBean.getDepinfoname()+"%'");
		}
		else if(!"".equals(depBean.getDepinfopreoftech())&&depBean.getDepinfopreoftech()!=null) {
			sb.append(" and depinfopreoftech = "+depBean.getDepinfopreoftech());
		}
		else if(!"".equals(depBean.getDepinfoassteach())&&depBean.getDepinfoassteach()!=null) {
			sb.append(" and depinfoassteach = "+depBean.getDepinfoassteach());
		}
		
		conn = getDBConnect();
		
		try {
			pst = conn.prepareStatement(sb.toString());
			rs = pst.executeQuery();
			while(rs.next()) {
				DepInfoBean deps = new DepInfoBean();
				deps.setDepinfoid(rs.getInt(1)+"");
				deps.setDepinfocode(rs.getString(2));
				deps.setDepinfoname(rs.getString(3));
				deps.setDepinfopreoftech(rs.getInt(4)+"");
				deps.setDepinfoassteach(rs.getInt(5)+"");
				deps.setDepinfospnum(rs.getInt(6)+"");
				deps.setDepinfoclassnum(rs.getInt(7)+"");
				depList.add(deps);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConn();
		}
		return depList;
	}
	
	//����Ժϵ��Ϣ����
		public int update(DepInfoBean depBean) {
			int result = 0;
			
			String sql = "update depinfo set depinfocode = ?,depinfoname = ?,depinfopreoftech = ?,depinfoassteach = ? where depinfocode = ?";
			
			conn = getDBConnect();
			
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, depBean.getDepinfocode());
				pst.setString(2, depBean.getDepinfoname());
				pst.setInt(3, Integer.parseInt(depBean.getDepinfopreoftech()));
				pst.setInt(4, Integer.parseInt(depBean.getDepinfoassteach()));
				pst.setString(5, depBean.getDepinfocode());
				result = pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				closeConn();
			}
			return result;
		}
		
		
		
		public int delete(SpecilinfoBean bean) {
			int result = 1;
			
			SpecilInfoService spService = new SpecilInfoService();
			
			//��Ժϵid������в�ѯ
			List<SpecilinfoBean> spList = spService.select(bean);
			//���޽����ֱ��ɾ��Ժϵ
			if(spList.size()==0) {
				
				String sql = "delete from depinfo where depinfo.depinfoid=?";
				
				conn = getDBConnect();
				
				try {
					pst = conn.prepareStatement(sql);
					pst.setInt(1, Integer.parseInt(bean.getDepinfoid()));
					pst.executeUpdate();
					result = 0;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					closeConn();
				}
				
			}
			return result;
		}
		
		
		
		
		
		
		
}
