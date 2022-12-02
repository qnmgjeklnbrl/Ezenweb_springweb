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
               
import {BrowserRouter, Routes , Route ,Link} from 'react-router-dom';
import Signup from './member/Signup';
  export default function Index( props ){
  return  (
            <div>
                     <BrowserRouter>

                         <Header/>
                         <h3>메인페이지</h3>
                        
                        <Routes>
                            <Route path='/'/>
                             <Route path='/member/signup' element={<Signup/>}/>

                        </Routes>
                        <Footer/>
                    </BrowserRouter>    
            </div>
                   );
    }
               
               