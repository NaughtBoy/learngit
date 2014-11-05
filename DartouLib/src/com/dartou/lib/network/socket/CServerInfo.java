package com.dartou.lib.network.socket;

import com.dartou.lib.network.socket.infa.IServerInfo;

/**
 * 服务器信息
 * @author cyn
 */
public class CServerInfo implements IServerInfo {
	
	protected long m_nIP;
	protected int m_nPort;
	protected String m_szServer;
	protected String m_szName;
	protected String m_szPwd;
	protected String m_szDomain;
	protected String m_szQuery;
	protected Object m_vParams;
//	protected String m_szPath;
	
	public CServerInfo(){
		m_nIP			= 0;
		m_nPort			= 80;
		m_szServer		= "";
		m_szName		= "";
		m_szPwd			= "";
		m_szDomain		= "";
		m_szQuery		= "";
		m_vParams		= null;
	}

	@Override
	public void SetServerIP(long nIP) {
		m_nIP			= nIP;
	}

	@Override
	public void SetServer(String szServer) {
		m_szServer		= szServer;
	}

	@Override
	public void SetPort(int nPort) {
		m_nPort			= nPort;
	}

	@Override
	public void SetAccount(String szName) {
		m_szName		= szName;
	}

	@Override
	public void SetPwd(String szPwd) {
		m_szPwd			= szPwd;
	}

	@Override
	public void SetDomain(String szDomain) {
		m_szDomain		= szDomain;
	}

	@Override
	public void SetQuery(String szQuery) {
		m_szQuery		= szQuery;
	}

	public void SetParams(Object vParams) {
		m_vParams		= vParams;
		
	}

//	public void SetPath(String szPath) {
//		m_szPath		= szPath;
//	}

	@Override
	public long GetServerIP() {
		return m_nIP;
	}

	@Override
	public String GetServer() {
		return m_szServer;
	}

	@Override
	public int GetPort() {
		return m_nPort;
	}

	@Override
	public String GetAccount() {
		return m_szName;
	}

	@Override
	public String GetPwd() {
		return m_szPwd;
	}

	@Override
	public String GetDomain() {
		return m_szDomain;
	}

	@Override
	public String GetQuery() {
		return m_szQuery;
	}

	public Object GetParams() {
		
		return m_vParams;
	}

//	public String GetPath() {
//		return m_szPath;
//	}

}
