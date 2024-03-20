package com.miguel.wbsconcliente;

import com.miguel.wbsconcliente.client.NumberClient;
import com.miguel.wbsconcliente.config.NumberClientConfig;
import com.miguel.wbsconcliente.ws.numbers.NumberToDollars;
import com.miguel.wbsconcliente.ws.numbers.NumberToDollarsResponse;
import com.miguel.wbsconcliente.ws.numbers.NumberToWordsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.ws.client.WebServiceIOException;
import org.springframework.ws.soap.client.SoapFaultClientException;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NumberClientConfig.class, loader = AnnotationConfigContextLoader.class)
public class NumberClientLiveTest {

    @Autowired
    private NumberClient numberClient;

    @Test
    public void getDollarTest(){
        NumberToDollarsResponse testDollar;
        testDollar = numberClient.getDollar(BigDecimal.valueOf(500));
        assertEquals("five hundred dollars", testDollar.getNumberToDollarsResult());
    }

    @Test
    public void getWordTest(){
        NumberToWordsResponse testWord;
        testWord = numberClient.getWord(BigInteger.valueOf(500));
        assertEquals("five hundred ", testWord.getNumberToWordsResult());
    }

    @Test
    public void getDollarZeroTest(){
        NumberToDollarsResponse testDollar;
        testDollar = numberClient.getDollar(BigDecimal.valueOf(0));
        assertEquals("", testDollar.getNumberToDollarsResult());
    }

    @Test
    public void getWordZeroTest(){
        NumberToWordsResponse testWord;
        testWord = numberClient.getWord(BigInteger.valueOf(0));
        assertEquals("zero", testWord.getNumberToWordsResult());
    }

    @Test
    public void getDollarNegativeTest(){
        NumberToDollarsResponse testDollar;
        testDollar = numberClient.getDollar(BigDecimal.valueOf(-500));
        assertNotEquals("minus five hundred dollars", testDollar.getNumberToDollarsResult());
    }

    @Test
    public void getWordNegativeTest(){
        NumberToWordsResponse testWord;
        testWord = numberClient.getWord(BigInteger.valueOf(-500));
        assertNotEquals("minus five hundred", testWord.getNumberToWordsResult());
    }

    @Test(expected= SoapFaultClientException.class)
    public void getDollarNullTest(){
        NumberToDollarsResponse testDollar;
        testDollar = numberClient.getDollar(null);
    }

    @Test(expected=SoapFaultClientException.class)
    public void getWordNullTest(){
        NumberToWordsResponse testWord;
        testWord = numberClient.getWord(null);
    }

    @Test
    public void getExceedDollarTest(){
        NumberToDollarsResponse testDollar;
        BigDecimal num = new BigDecimal("2000000000000000000");
        testDollar = numberClient.getDollar(num);
        assertEquals("", testDollar.getNumberToDollarsResult());
    }

    @Test
    public void getExceedWordTest(){
        NumberToWordsResponse testWord;
        BigInteger num = new BigInteger("2000000000000000000");
        testWord = numberClient.getWord(num);
        assertEquals("number too large", testWord.getNumberToWordsResult());
    }
}
