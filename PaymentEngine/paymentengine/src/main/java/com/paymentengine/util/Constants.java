package com.paymentengine.util;

public interface Constants {
	
	public static enum ProductStatus {
		New,Upgrade,Activate
	}
	
	public static enum ProductTypes {
		Book,PhysicalProduct,Membership,Video
	}
	
	public static enum OrderPromotion {
		FreeVideo,None
	}
	
	public static enum AcknowledgementMode {
		PackingSlip,Email
	}
	
	
	

}
