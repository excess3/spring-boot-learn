package com.springboot.learnspringboot.filtering;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @RequestMapping(method = RequestMethod.GET, path = "/filtering")
    public FilteredBean filtering()
    {
        return new FilteredBean("Value1", "Value2", "Value3");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/filtering-list")
    public List<FilteredBean> filteringList()
    {
        return Arrays.asList( new FilteredBean("Value1", "Value2", "Value3"),  new FilteredBean("Value4", "Value5", "Value6"));
    }
}
