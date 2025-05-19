package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.DataBaseConnection;
import model.TableOrder;

class TestUpdateTableOrder
{
	TableOrder testOrder;
	Connection con = DataBaseConnection.getInstance().getConnection();
	private boolean startIsRequestingService = false;
	private boolean startIsSentToKitchen;
	private boolean startIsTableOrderClosed;
	private int startOrderPreparationTime;
	private double startTotalAmountPaid;
	private double startTotalTableOrderPrice;
	private String startPaymentType;
	private LocalDateTime startTimeOfArrival;
	
	private int tableOrderId;

	@BeforeEach
	void setUp() throws Exception
	{
		con.setAutoCommit(false);
	}

	@AfterEach
	void tearDown() throws Exception
	{
		con.rollback();
		con.setAutoCommit(true);
	}

	@Test
	void test()
	{
		fail("Not yet implemented");
	}

}
