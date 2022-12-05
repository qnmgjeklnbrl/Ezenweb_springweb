import React from "react";
import Notification from "./Notification";


const reservedNotifications =[
    {message:"안녕하세요, 오늘 일정 알려드립니다."},
    {message:"점심 식사 시간입니다."},
    {message:"이제 곧 미팅이 시작됩니다."}
];

var timer;

class NotificationList extends React.Component{
    constructor(props){
        super(props);
        this.state = {notifications:[]};

    }
    componentDidMount(){
        const{notifications} = this.state;//생명주기
        timer = setInterval( () => {
         
            if(notifications.length<reservedNotifications.length){
                const index = notifications.length;
                notifications.push(reservedNotifications[index]);
                this.setState( {
                    notifications:notifications
                });
               
                
            }else{
                clearInterval(timer);
            }

        },1500);
    }
    render(){
        return(
            <div>
                {this.state.notifications.map( (notification) =>{
                   return <Notification message={notification.message}/>;
                } )}
            </div>
        )
    }

}
export default NotificationList;