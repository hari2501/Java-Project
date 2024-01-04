package com.secure.Interface;

import java.util.List;

import com.secure.bean.Register;

public interface Interface {
	
	public int datasharingRegister(Register datareg);
	public int datasharingLogin(String username,String password);
	public int datareceiverRegister(Register dataReceiverRegister);
	public int datareciverLogin(String username, String password);

}
