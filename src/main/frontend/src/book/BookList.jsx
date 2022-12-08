import React from "react";
import { HashRouter, BrowserRouter, Routes, Route, Link,  Router } from "react-router-dom";

export default function BookList(props){

    return(

        <div>
            <ul>
                <li>
                   
                    <li> <Link to="/booklist/chapter3" > chapter3   </Link> </li>
                    <li> <Link to="/booklist/chapter4" > chapter4   </Link> </li>
                    <li> <Link to="/booklist/chapter5" > chapter5   </Link> </li>
                    <li> <Link to="/booklist/chapter6" > chapter6   </Link> </li>
                    <li> <Link to="/booklist/chapter7" > chapter7   </Link> </li>
                    <li> <Link to="/booklist/chapter8" > chapter8   </Link> </li>
                    <li> <Link to="/booklist/chapter9" > chapter9   </Link> </li>
                  




                </li>



            </ul>


        </div>

    );



    
}