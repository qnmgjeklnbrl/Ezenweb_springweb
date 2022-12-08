// jsx : 리액트 확장 표현식 파일
// 컴포넌트 단위 애플리케이션 제작
// SPA : 싱글 페이지 애플리케이션 [ 페이지는 하나다 ]
    // 라우터 라이브러리 : 가상 URL
// 컴포넌트 만들기 준비물
    // 1. 첫글자는 대문자 [ 컴포넌트명 == 파일명 ]
    // 2. 리액트[프레임워크가 아니다] 라이브러리 집합소 [ import 많다 ]
        // 1. import React from 'react';
        // 2. function 컴포넌트명(){  return ( 렌더링할 코드 ); }
        // 3. export default 컴포넌트명 ;
               // 2,3 : export default function 컴포넌트명(){  return ( 렌더링할 코드 ); }



import React from 'react';
import Header from './Header';
import Footer from './Footer';
import Home from './Home';
               
import {BrowserRouter, Routes , Route ,Link} from 'react-router-dom';
import Signup from './member/Signup';
import Login from './member/Login';
import BoardList from './board/BoardList';
import Library from '../book/chapter3/Library';
import Clock from '../book/chapter4/Clock';
import CommentList from '../book/chapter5/CommentList';
import NotificationList from '../book/chapter6/NotificationList';
import Accommodate from '../book/chapter7/Accomodate';
import ConfirmButton2 from '../book/chapter8/Confirmbutton2';
import LandingPage from '../book/chapter9/LandingPage';
import BookList from '../book/BookList';
  export default function Index( props ){
  return  (
            <div>
                     <BrowserRouter>

                         <Header/>
                         
                        
                        <Routes>
                            <Route path='/'element={<Home/>}/>
                            <Route path='/member/signup' element={<Signup/>}/>
                            <Route path='/member/login' element={<Login/>}/>
                            <Route path='/board/list' element={<BoardList/>}/>
                            <Route path='/booklist' element={<BookList/>}/>
                            <Route path='/booklist/chapter3' element={<Library/>}/>
                            <Route path='/booklist/chapter4' element={<Clock/>}/>
                            <Route path='/booklist/chapter5' element={<CommentList/>}/>
                            <Route path='/booklist/chapter6' element={<NotificationList/>}/>
                            <Route path='/booklist/chapter7' element={<Accommodate/>}/>
                            <Route path='/booklist/chapter8' element={<ConfirmButton2/>}/>
                            <Route path='/booklist/chapter9' element={<LandingPage/>}/>



                           {/* <Route path='/board/write' element={<BoardWrite/>}/> */}
                        </Routes>
                        <Footer/>
                    </BrowserRouter>    
            </div>
                   );
    }
               
               