package com.springboot.learnspringboot.filtering;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @RequestMapping(method = RequestMethod.GET, path = "/filtering")
    public MappingJacksonValue filtering()
    {
        FilteredBean filteredBean = new FilteredBean("Value1", "Value2", "Value3");

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(filteredBean); // utility that help us to set filters

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/filtering-list")
    public List<FilteredBean> filteringList()
    {
        return Arrays.asList( new FilteredBean("Value1", "Value2", "Value3"),  new FilteredBean("Value4", "Value5", "Value6"));
    }
}
