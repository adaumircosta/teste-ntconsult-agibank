package br.com.ntconsult.file.listener.domain.constants;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileConstant {

    public static final Path PATH_IN = Paths.get("in");
    public static final Path PATH_OUT = Paths.get("out");

    public static final String EXTENSION_DAT = ".dat";
    public static final String EXTENSION_DONE_DAT = ".dat.proc";

    public static final Integer PLACE_SALESMAN_CPF = 1;
    public static final Integer PLACE_SALESMAN_NAME = 2;
    public static final Integer PLACE_SALESMAN_SALARY = 3;

    public static final Integer PLACE_CUSTOMER_CNPJ = 1;
    public static final Integer PLACE_CUSTOMER_NAME = 2;
    public static final Integer PLACE_CUSTOMER_BUSINESS_AREA = 3;

    //(0	;	1			;	2			;	3
    //(003	;	ID da venda	;	[items]		;	Nome do Vendedor)
    public static final Integer PLACE_SALE_ID = 1;
    public static final Integer PLACE_SALE_ITENS = 2;
    public static final Integer PLACE_SALE_SALESMAN = 3;

    //[0	,	1		,	2			]
    //[id	,	preço	,	quantidade	]
    public static final Integer PLACE_SALE_ITEM_ID = 0;
    public static final Integer PLACE_SALE_ITEM_QUANTITY = 1;
    public static final Integer PLACE_SALE_ITEM_PRICE = 2;
}
