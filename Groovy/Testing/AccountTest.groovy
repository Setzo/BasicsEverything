package groovy

import groovy.util.GroovyTestCase
import groovy.mock.interceptor.*


class AccountTest extends GroovyTestCase {

	def private account
	
	def void setUp() {
		account = new Account(100)
	}
	
	def void tearDown() {
		account = null
	}
	
	def void testCanDepositMoney() {
		
		account.deposit(100)
		assert 200 == account.balance
	}
	
	def void testCanWithdrawMoney() {
		
		account.withdraw(100)
		assert 0 == account.balance
	}
	
	def void testCanNotWithdrawMoreMoneyThanBalance() {
		
		shouldFail(InsuficientFundsException) {
			account.withdraw(200)
		}
	}
	
	def void testCanAccrueInterest() {
		
//		def service = new StubFor(InterestRateService)
		def service = new MockFor(InterestRateService)
		
		service.demand.getInterestRate {
			return 0.1
		}
		
		service.use {
			account.accrueInterest()
			assert 110 == account.balance
		}
	}

}
