package resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Transaction;
import service.TransactionService;
import spark.Request;
import spark.Response;

public class TransactionResource {

	private TransactionService transactionService;

	public TransactionResource() {
		this.transactionService = new TransactionService();
	}

	public List<Transaction> findAll(Request request, Response response) {
		response.status(HttpServletResponse.SC_OK);

		List<Transaction> account = new ArrayList<Transaction>();

		account = this.transactionService.findAll();

		return account;
	}

	public List<Transaction> findAllTransactionByAccount(Request request, Response response) {
		response.status(HttpServletResponse.SC_OK);

		Long id = Long.parseLong(request.params(":id"));
		String operation = request.queryParams("f");

		List<Transaction> account = new ArrayList<Transaction>();

		if (operation.equals("t")) {
			account = this.transactionService.findAllTransactionByToAccount(id);
		} else {
			account = this.transactionService.findAllTransactionByFromAccount(id);
		}

		return account;
	}

	public Transaction save(Request request, Response response) {
		response.status(HttpServletResponse.SC_CREATED);

		Transaction transaction = new Gson().fromJson(request.body(), Transaction.class);

		Long id = this.transactionService.create(transaction);

		return new Transaction(id, transaction.getFromAccount(), transaction.getToAccount(), transaction.getAmount());
	}
}
