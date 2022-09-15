package org.navistack.framework.mybatisplusplus.query;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
@QueryEntity
public class TestQueryEntity {
    @QueryCondition(operator = Operator.GREATER_THAN)
    @QueryColumn(name = "property_1")
    private String property1;

    @QueryCondition(operator = Operator.GREATER_THAN_OR_EQUAL_TO)
    @QueryColumn(name = "property_2")
    private String property2;

    @QueryCondition(operator = Operator.LESS_THAN)
    @QueryColumn(name = "property_3")
    private String property3;

    @QueryCondition(operator = Operator.LESS_THAN_OR_EQUAL_TO)
    @QueryColumn(name = "property_4")
    private String property4;

    @QueryCondition(operator = Operator.NOT_EQUAL_TO)
    @QueryColumn(name = "property_5")
    private String property5;

    @QueryCondition(operator = Operator.IS_NULL_OR_NOT_EQUAL_TO)
    @QueryColumn(name = "property_6")
    private String property6;

    @QueryCondition(operator = Operator.EQUAL_TO)
    @QueryColumn(name = "property_7")
    private String property7;

    @QueryCondition(operator = Operator.IS_NOT_NULL_AND_EQUAL_TO)
    @QueryColumn(name = "property_8")
    private String property8;

    @QueryCondition(operator = Operator.BETWEEN_IN)
    @QueryColumn(name = "property_9")
    private Collection<String> property9;

    @QueryCondition(operator = Operator.NOT_BETWEEN_IN)
    @QueryColumn(name = "property_10")
    private String[] property10;

    @QueryCondition(operator = Operator.IN)
    @QueryColumn(name = "property_11")
    private List<String> property11;

    @QueryCondition(operator = Operator.NOT_IN)
    @QueryColumn(name = "property_12")
    private List<String> property12;

    @QueryCondition(operator = Operator.LIKE)
    @QueryColumn(name = "property_13")
    private String property13;

    @QueryCondition(operator = Operator.LEFT_LIKE)
    @QueryColumn(name = "property_14")
    private String property14;

    @QueryCondition(operator = Operator.RIGHT_LIKE)
    @QueryColumn(name = "property_15")
    private String property15;

    @QueryCondition(operator = Operator.REGEXP)
    @QueryColumn(name = "property_16")
    private String property16;

    @QueryCondition(operator = Operator.NOT_REGEXP)
    @QueryColumn(name = "property_17")
    private String property17;

    @QueryCondition(operator = Operator.NOT_LIKE)
    @QueryColumn(name = "property_18")
    private String property18;

    @QueryCondition(
            operator = Operator.EXPRESSION,
            expression = "DATE_FORMAT({0},'%Y-%m') = DATE_FORMAT({1},'%Y-%m')"
    )
    @QueryColumn(name = "property_19")
    private String property19;

    private String property20;

    @QueryCondition(operator = Operator.EQUAL_TO)
    @QueryColumn(name = "property_21")
    private String property21;
}
