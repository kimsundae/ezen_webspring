import * as React from 'react';
import Box from '@mui/material/Box';
import Tab from '@mui/material/Tab';
import TabContext from '@mui/lab/TabContext';
import TabList from '@mui/lab/TabList';
import TabPanel from '@mui/lab/TabPanel';

import CategoryWrite from "./CategoryWrite";
import ProductInfo from "./ProductInfo";
import ProductList from "./ProductList";
import ProductWrite from "./ProductWrite";
import axios from "axios";
import {useEffect, useState} from "react";

export default function ProductAdmin(props){

    // 0. 출력할 카테고리 목록
    const [categoryList, setCategoryList] = useState([]);
    // 2. 카테고리 출력
    const printCategory = (e) => {
        axios.get('/product/category').then(r=>{console.log(r.data); setCategoryList(r.data)} )
    }
    useEffect(() => {
        printCategory()
    }, []);
    const [value, setValue] = React.useState('1');

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    return (
        <Box sx={{ width: '100%', typography: 'body1' }}>
            <TabContext value={value}>
                <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                    <TabList
                        onChange={handleChange} aria-label="lab API tabs example"
                        indicatorColor="secondary"
                        centered>
                        <Tab label="제품 등록" value="2" />
                        <Tab label="제품 목록" value="3" />
                        <Tab label="제품 상태" value="4" />
                    </TabList>
                </Box>
                <TabPanel value="1"><CategoryWrite categoryList={categoryList} printCategory={printCategory} /></TabPanel>
                <TabPanel value="2"><ProductWrite categoryList={categoryList} printCategory={printCategory}/></TabPanel>
                <TabPanel value="3"><ProductList categoryList={categoryList} printCategory={printCategory}/></TabPanel>
                <TabPanel value="4"><ProductInfo categoryList={categoryList} printCategory={printCategory}/></TabPanel>
            </TabContext>
        </Box>
    );
}