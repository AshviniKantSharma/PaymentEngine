package com.paymentengine.service;

import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentengine.model.Order;
import com.paymentengine.model.Orders;
import com.paymentengine.model.PaymentInitiationRequest;
import com.paymentengine.model.PaymentResponse;
import com.paymentengine.model.Product;
import com.paymentengine.util.Constants;

/**PaymentServiceImpl : Class to implement service level handling of Payment Engine
 * @author Ashvini
 *
 */
@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {


	@Autowired
	Orders ordersObj;
	
	@Override
	public PaymentResponse facilitatePaymentAndOrder(com.paymentengine.model.PaymentInitiationRequest paymentReqObj) throws Exception {
		// TODO Auto-generated method stub
		
		PaymentResponse payResp = null;
		
		if(ordersObj.equals(null)) {
			
			ordersObj = new Orders();
		}
		
		boolean orderExisting = ordersObj.getOrderList().stream()
		.filter( orderObj -> orderObj.getId().equals(paymentReqObj.getOrderId()))
		.findFirst().isPresent();
		
		log.info("Order Existing ?"+orderExisting);
		
		if(orderExisting) {
			
			Order orderObj = ordersObj.getOrderList().stream()
			.filter( listOrderObj -> listOrderObj.getId().equals(paymentReqObj.getOrderId()))
			.findFirst()
			.orElse(null);
			
			if(orderObj.getProdObj().getName().equals(Constants.ProductTypes.Membership.toString()) &&
					orderObj.getProdObj().getStatus().equals(Constants.ProductStatus.New.toString())) {
				log.info("Membership Upgraded");
				orderObj.getProdObj().setStatus(Constants.ProductStatus.Upgrade.toString());
				payResp = constructPayResponse(orderObj.getId(),orderObj.getAcknowledgementMode(),orderObj.getPromotion());
			}
			
			ordersObj.getOrderList().add(orderObj);
			
		}else {
			
			Order orderObj;
			Product prodObj;
			
			if(paymentReqObj.getPaymentType().equals(Constants.ProductTypes.Book.toString())) {
				prodObj = new Product(Constants.ProductTypes.Book.toString(), Constants.ProductStatus.New.toString());
				orderObj = new Order(uuidGenerator(), Constants.AcknowledgementMode.PackingSlip.toString(), Constants.OrderPromotion.None.toString(),Boolean.TRUE, prodObj);
				payResp = constructPayResponse(orderObj.getId(),orderObj.getAcknowledgementMode(),orderObj.getPromotion());
				ordersObj.getOrderList().add(orderObj);
			}else if(paymentReqObj.getPaymentType().equals(Constants.ProductTypes.PhysicalProduct.toString())) {
				prodObj = new Product(Constants.ProductTypes.PhysicalProduct.toString(), Constants.ProductStatus.New.toString());
				orderObj = new Order(uuidGenerator(), Constants.AcknowledgementMode.PackingSlip.toString(), Constants.OrderPromotion.None.toString(),Boolean.TRUE, prodObj);
				payResp = constructPayResponse(orderObj.getId(),orderObj.getAcknowledgementMode(),orderObj.getPromotion());
				ordersObj.getOrderList().add(orderObj);
			}else if(paymentReqObj.getPaymentType().equals(Constants.ProductTypes.Membership.toString())) {
				prodObj = new Product(Constants.ProductTypes.Membership.toString(), Constants.ProductStatus.New.toString());
				orderObj = new Order(uuidGenerator(), Constants.ProductStatus.Activate.toString(), Constants.OrderPromotion.None.toString(),Boolean.FALSE, prodObj);
				payResp = constructPayResponse(orderObj.getId(),orderObj.getAcknowledgementMode(),orderObj.getPromotion());
				ordersObj.getOrderList().add(orderObj);
			}else if(paymentReqObj.getPaymentType().equals(Constants.ProductTypes.Video.toString())) {
				prodObj = new Product(Constants.ProductTypes.Video.toString(), Constants.ProductStatus.New.toString());
				orderObj = new Order(uuidGenerator(), Constants.AcknowledgementMode.PackingSlip.toString(), Constants.OrderPromotion.FreeVideo.toString(),Boolean.FALSE, prodObj);
				payResp = constructPayResponse(orderObj.getId(),orderObj.getAcknowledgementMode(),orderObj.getPromotion());
				ordersObj.getOrderList().add(orderObj);
			}else {
				throw new Exception("Product Type not supported");
			}
		}
		return payResp;
	}
	
	private PaymentResponse constructPayResponse(String orderId,String orderStatus,String promotionMessage) {
		
		
		PaymentResponse payResp = new PaymentResponse();
		payResp.setOrderId(orderId);
		payResp.setStatus(orderStatus);
		payResp.setPromotionMessage(promotionMessage);
		return payResp;
	}



}
