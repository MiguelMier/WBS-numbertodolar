package com.miguel.wbsconcliente.client;

import com.miguel.wbsconcliente.ws.numbers.NumberToDollars;
import com.miguel.wbsconcliente.ws.numbers.NumberToDollarsResponse;
import com.miguel.wbsconcliente.ws.numbers.NumberToWords;
import com.miguel.wbsconcliente.ws.numbers.NumberToWordsResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberClient extends WebServiceGatewaySupport {

    public NumberToDollarsResponse getDollar(BigDecimal value){
        NumberToDollars request = new NumberToDollars();
        request.setDNum(value);

        return (NumberToDollarsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public NumberToWordsResponse getWord(BigInteger value){
        NumberToWords request = new NumberToWords();
        request.setUbiNum(value);

        return (NumberToWordsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }
}
