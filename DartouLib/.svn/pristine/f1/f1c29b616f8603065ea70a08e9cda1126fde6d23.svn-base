package com.dartou.lib.network.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import android.R.id;
import android.os.Handler;
import android.util.Log;

import com.dartou.lib.network.socket.dian.BigDian;
import com.dartou.lib.network.socket.dian.IDian;
import com.dartou.lib.network.socket.dian.SmallDian;
import com.dartou.lib.network.socket.infa.IParser;
import com.dartou.lib.network.socket.infa.IServerInfo;
import com.dartou.lib.network.socket.security.BasicCode;
import com.dartou.lib.network.socket.security.ICode;
import com.dartou.lib.utils.LogUtil;

/**
 * socket连接类 send---负责把传入的数据包发送出去 ReadPackages--负责接手数据包并且返回
 * 
 * @author cyn
 * 
 */
public class CConnection {

	// 连接状态定义
	public static final int CN_STATUS_NOCONNECT = 0;
	public static final int CN_STATUS_CONNECTING = 1;
	public static final int CN_STATUS_CONNECTED = 10;
	public static final int CN_STATUS_ERROR = -1;

	private  Socket m_cSocket = null;
	private OutputStream m_vOutStream = null;
	private DataInputStream m_vDataInStream = null;

	protected IServerInfo m_vServerInfo = null;

	protected int m_nStatus = 0; // 连接状态

	private IParser mIParser;
	public CConnection(IParser iParser) {
		m_nStatus = CN_STATUS_NOCONNECT;
		this.mIParser = iParser;
	}

	public static IDian iDian = new SmallDian();
	/**
	 *设置是否大端
	 */
	public void setBigDian(boolean isBigDian){
		if(isBigDian){
			iDian = new BigDian();
		}else{
			iDian = new SmallDian();
		}
	}
	
	public  ICode icode = new BasicCode();
	/**
	 * 设置加密和解密接口
	 * @param code
	 */
	public void setCode(ICode code){
		if(code==null){
			icode = new BasicCode();
		}else{
			this.icode = code;
		}
	}
	
	/**
	 * 服务器信息配置器，在连接服务器前，要先配置好连接参数
	 * 
	 * @param vServerInfo
	 */
	public void SetServerInfo(IServerInfo vServerInfo) {
		this.m_vServerInfo = vServerInfo;
	}

	/**
	 * 
	 * 返回服务器配置信息
	 */
	public IServerInfo GetServerInfo() {
		return this.m_vServerInfo;
	}


	private Handler m_timeHandler;

	/**
	 * 設置當前界面的接收倒計時開始的接收器
	 * 
	 * @param vHandler
	 */
	public void SetStartTimeHandler(Handler vHandler) {
		m_timeHandler = vHandler;
	}

	/**
	 * 创建并连接服务器,初始化只调用一次并且必须serverinfo要有数据
	 */
	public boolean initconnection() {

		try {
			if (m_vServerInfo == null)
				return false;
			// 实例化Socket
			// if(m_cSocket!=null&&!m_cSocket.isClosed()){
			// CloseSocket();
			// m_cSocket = null;
			// }
			m_cSocket = new Socket(m_vServerInfo.GetServer(),
					m_vServerInfo.GetPort());
			// InetSocketAddress isa = new
			// InetSocketAddress(m_vServerInfo.GetServer(),
			// m_vServerInfo.GetPort());
			// m_cSocket.connect(isa, 100000);
			// 获取输出流
			m_vOutStream = m_cSocket.getOutputStream();
			// 获得输入流
			// m_vInStream = m_cSocket.getInputStream();
			m_vDataInStream = new DataInputStream(m_cSocket.getInputStream());
			// 连接状态-连接中...
			m_nStatus = CConnection.CN_STATUS_CONNECTING;

			new Thread(new ThreadRun()).start();

			if (m_cSocket!=null&&m_cSocket.isConnected()) {
				// 回调
				return true;
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			m_nStatus = CConnection.CN_STATUS_ERROR;
			// Toast.makeText(m_cContext, "服务器IP地址无法访问",
			// Toast.LENGTH_SHORT).show();
			Log.e("NetworkSocket", "服务器IP地址无法访问");
		} catch (SocketTimeoutException e) {
			Log.e("NetworkSocket", "服务器socket超时");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 连接状态-无法连接服务器
			m_nStatus = CConnection.CN_STATUS_ERROR;
			// Toast.makeText(m_cContext, "无法连接服务器", Toast.LENGTH_SHORT).show();
			Log.e("NetworkSocket", "无法连接服务器");
			// Handler h = CCommondData.getInstance().GetGameLoadingHandler();
			// if(h!=null){
			// h.sendMessage(h.obtainMessage());
			// }
		}
		return false;
	}

	private Handler vHandler = null;

	private Runnable vRun = null;

	/**
	 * 断开socket
	 */
	public void CloseSocket() {
		try {
			if (m_cSocket != null) {
				m_cSocket.close();
				m_cSocket = null;
				LogUtil.i("", "NetWork-->Socket断开-->");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 发送数据
	 * 
	 * @param vPacke
	 * @return
	 */
	public void Send(byte[] packet) {
		SendData(packet);
	}

	/**
	 * 数据加包头的处理
	 */
	public Object OnAddHeader(Object vData, Object vParams) {
		return vData;
	}

	/**
	 * 各种类型的连接的发送处理
	 * 
	 * 该流程中只关注不同链接的发送的处理，不再作格式化或任何设置
	 * 
	 * @param vData
	 */
	private void SendData(byte[] vData) {

		// LogUtil.e("NetworkSocket", "data->"+s);
		// byte[] temp = new byte[vData.length];
		// for (int i = 0; i < temp.length; i++) {
		// if(vData[i]<0){
		// vData[i]*=-1;
		// }
		// }
		try {
			if (m_cSocket!=null&&m_cSocket.isConnected() && m_vOutStream != null) {
				// ByteUtil.printHexString(vData);
				m_vOutStream.write(vData);
				m_vOutStream.flush();
			} else {
				CloseSocket();
				mIParser.networkBroken();
			}
		} catch (IOException e) {
			CloseSocket();
			mIParser.networkBroken();
		}
	}

	/**
	 * 获取状态
	 * 
	 * @return int
	 * 
	 */
	public int GetStatus() {
		return m_nStatus;
	}

	/**
	 * 判断是否已连接服务器
	 * 
	 * @return
	 * 
	 */
	public Boolean IsConnected() {
		// return m_nStatus>=CN_STATUS_CONNECTED;
		return true;
	}

	/**
	 * 释放资源
	 */
	public void Destroy() {
		// 先断开链接再释放变量
		Disconnect(false);
		m_vServerInfo = null;
	}

	/**
	 * 断开连接
	 * 
	 * @param bForce
	 * @return
	 * 
	 */
	public Boolean Disconnect(Boolean bForce) {
		CloseSocket();
		m_nStatus = CN_STATUS_NOCONNECT;
		return true;
	}

	private int nbuflen = 0;


	class ThreadRun implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while (true) {
					Thread.sleep(50);
					if (!m_cSocket.isClosed()) {
						if (m_cSocket.isConnected()) {
							if (m_vDataInStream.available() != 0) {
								
								Packet packet = mIParser.body(m_vDataInStream);
								mIParser.parse(packet.cmd, packet.body);
							}
						} else {
							CloseSocket();
							mIParser.networkBroken();
							LogUtil.e("Connection", "服务断开");
							break;
						}
					}else{
						CloseSocket();
						mIParser.networkBroken();
						break;
					}
				}
			} catch (Exception e) {
				CloseSocket();
				mIParser.networkBroken();
				e.printStackTrace();
			}
		}
	}
	
	public boolean isConnect(){
		System.out.println("NetWork-->6-->"+(m_cSocket==null));
		return m_cSocket!=null&&(!m_cSocket.isClosed())&&m_cSocket.isConnected();
	}

}
