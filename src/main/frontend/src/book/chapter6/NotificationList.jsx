// 1. import : 상대경로 [ 현재파일 위치 기준 ./ : 현재폴더     ../ 상위폴더 ]
import React from 'react'
import Notification from './Notification'

// 2. data
const reservedNotifications = [
    {
        id: 1,
        message: "안녕하세요, 오늘 일정을 알려드립니다.",
    },
    {
        id: 2,
        message: "점심식사 시간입니다.",
    },
    {
        id: 3,
        message: "이제 곧 미팅이 시작됩니다.",
    },
];
// 3. 타이머 변수 [ Interval ]
var timer
// 4. 클래스를 이용한 컴포넌트 만들기
class NotificationList extends React.Component{
    // 1. 생성자
    constructor(props){
        super(props);
        this.state = { notifications : [  ] }; // 비어 있음
    }
    // 2. 함수1 [ 컴포넌트 생성 ]
    componentDidMount(){
        const { notifications } = this.state; // 생명주기
        // timer = setInterval( () => { 실행코드 } , 1000 ) ;
        timer = setInterval(() => {
            if (notifications.length < reservedNotifications.length) {
                const index = notifications.length;
                notifications.push(reservedNotifications[index]);
                this.setState({
                    notifications: notifications,
                });
            } else {
                this.setState({  notifications: [],  }); // 초기화
            }
        }, 1000); // 1 초
    } // 함수1 end

    // 추가코드  // NotificationList 컴포넌트 사망시[끝났을때] timer 클리어
    componentWillUnmount(){
        if( timer ) {   // timer 변수에 setInterval 함수가 있으면
            clearInterval( timer ); // setInterval 종료
        }
    }

    // 3. 함수2
    render(){
        return (
            <div>
                { this.state.notifications.map( ( n ) => {
                    return <Notification key={n.id} id={ n.id } message={ n.message } />;
                } ) }
            </div>
        )
    }
}
// 3.
export default NotificationList;


/*
    리스트변수명.forEach(  (반복변수명) => { 실행문 }; }
    리스트변수명.map(   (반복변수명) => { return 반환값 };  )
*/