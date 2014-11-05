package com.dartou.lib.network.socket.infa;

/**
 * 服务器信息接口
 * @author cyn
 *
 */
public interface IServerInfo {
	public void SetServerIP(long nIP);
	public void SetServer(String szServer);
	public void SetPort(int nPort);
	public void SetAccount(String szName);
	public void SetPwd(String szPwd);
	public void SetDomain(String szDomain);
	public void SetQuery(String szQuery);
	public void SetParams(Object vParams);
//	public void SetPath(String szPath);
	
	public long GetServerIP();
	public String GetServer();
	public int GetPort();
	public String GetAccount();
	public String GetPwd();
	public String GetDomain();
	public String GetQuery();
	public Object GetParams();
//	public String GetPath();
}
