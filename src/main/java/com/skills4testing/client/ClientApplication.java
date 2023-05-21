package com.skills4testing.client;

import com.skills4testing.exchange.order.query.COrderExecuteQuery;

public class ClientApplication {

	
	
	 private static int mPort = 6688;
	 private static String mHost = "localhost";
	    
	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("I am clinet ..... Sending message to the server .. ");
		
		int 	mBlockOrderID = 100;
		String 	mSecuritySymbol = "BT";
		int 	mSecurityId = 10;
		String 	mOrderSide = "BUY";
		int 	mTradeQty = 1000;
		String 	mOrderType = "MarketPrice";
		int 	mTradePrice = 25;
		
		try {
				COrderExecuteQuery orderExecuteQuery = new COrderExecuteQuery();
				
				orderExecuteQuery.setBlockOrderID(String.valueOf(mBlockOrderID));
				orderExecuteQuery.setSecuritySymbol(mSecuritySymbol);
				orderExecuteQuery.setSecurityId(String.valueOf(mSecurityId));
				orderExecuteQuery.setOrderSide(mOrderSide);
				orderExecuteQuery.setTradeQty(String.valueOf(mTradeQty));
				orderExecuteQuery.setOrderType(mOrderType);
				orderExecuteQuery.setTradePrice(String.valueOf(mTradePrice));
				
				
				
				// Create connection
				
				CConnectionHandler connectBrokerExchange = new CConnectionHandler(mHost, mPort);
				
				String response = connectBrokerExchange.handleRequest(orderExecuteQuery.toXML());
				
				//print the return message
				
				System.out.println("Printing the response message fromt he server ###########");
				System.out.println(response);
				
		} catch (Exception e) {
			
		}
		
		
	}

}
