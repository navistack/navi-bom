package org.navistack.framework.mybatisplusplus.query;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class QueryWrapperConverterTest {

    @Test
    void testConvert() {
        TestQueryEntity entity = new TestQueryEntity();
        entity.setProperty1("value1");
        entity.setProperty2("value2");
        entity.setProperty3("value3");
        entity.setProperty4("value4");
        entity.setProperty5("value5");
        entity.setProperty6("value6");
        entity.setProperty7("value7");
        entity.setProperty8("value8");
        entity.setProperty9(Arrays.asList("value9_1", "value9_2"));
        entity.setProperty10(new String[]{"value10_1", "value10_2"});
        entity.setProperty11(Arrays.asList("value11_1", "value11_2", "value11_3"));
        entity.setProperty12(Arrays.asList("value12_1", "value12_2", "value12_3"));
        entity.setProperty13("value13");
        entity.setProperty14("value14");
        entity.setProperty15("value15");
        entity.setProperty16("value16");
        entity.setProperty17("value17");
        entity.setProperty18("value18");
        entity.setProperty19("value19");
        entity.setProperty20("value20");

        QueryWrapperConverter<TestQueryEntity> converter = new QueryWrapperConverter<>();
        QueryWrapper<TestQueryEntity> queryWrapper = converter.convert(entity);

        String actual = queryWrapper.getExpression().getNormal().getSqlSegment();
        String expected = "("
                + "property_1 > #{ew.paramNameValuePairs.MPGENVAL1}"
                + " AND "
                + "property_2 >= #{ew.paramNameValuePairs.MPGENVAL2}"
                + " AND "
                + "property_3 < #{ew.paramNameValuePairs.MPGENVAL3}"
                + " AND "
                + "property_4 <= #{ew.paramNameValuePairs.MPGENVAL4}"
                + " AND "
                + "property_5 <> #{ew.paramNameValuePairs.MPGENVAL5}"
                + " AND "
                + "(property_6 IS NULL OR property_6 <> #{ew.paramNameValuePairs.MPGENVAL6})"
                + " AND "
                + "property_7 = #{ew.paramNameValuePairs.MPGENVAL7}"
                + " AND "
                + "(property_8 IS NOT NULL AND property_8 = #{ew.paramNameValuePairs.MPGENVAL8})"
                + " AND "
                + "property_9 BETWEEN #{ew.paramNameValuePairs.MPGENVAL9} AND #{ew.paramNameValuePairs.MPGENVAL10}"
                + " AND "
                + "property_10 NOT BETWEEN #{ew.paramNameValuePairs.MPGENVAL11} AND #{ew.paramNameValuePairs.MPGENVAL12}"
                + " AND "
                + "property_11 IN (#{ew.paramNameValuePairs.MPGENVAL13},#{ew.paramNameValuePairs.MPGENVAL14},#{ew.paramNameValuePairs.MPGENVAL15})"
                + " AND "
                + "property_12 NOT IN (#{ew.paramNameValuePairs.MPGENVAL16},#{ew.paramNameValuePairs.MPGENVAL17},#{ew.paramNameValuePairs.MPGENVAL18})"
                + " AND "
                + "property_13 LIKE #{ew.paramNameValuePairs.MPGENVAL19}"
                + " AND "
                + "property_14 LIKE #{ew.paramNameValuePairs.MPGENVAL20}"
                + " AND "
                + "property_15 LIKE #{ew.paramNameValuePairs.MPGENVAL21}"
                + " AND "
                + "property_16 REGEXP #{ew.paramNameValuePairs.MPGENVAL23}"
                + " AND "
                + "property_17 NOT REGEXP #{ew.paramNameValuePairs.MPGENVAL25}"
                + " AND "
                + "property_18 NOT LIKE #{ew.paramNameValuePairs.MPGENVAL26}"
                + " AND "
                + "DATE_FORMAT(property_19,'%Y-%m') = DATE_FORMAT(#{ew.paramNameValuePairs.MPGENVAL28},'%Y-%m')"
                + ")";
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
