import React from "react";
import Styles from "./Notification.css"

class Notification extends React.Component{

    constructor(props){

        super(props);
        this.state={};
    }
    render(){
        return(
            <div className="wrapper">
                <span className="messageText">
                   {this.props.message}
                </span>
            </div>
        )
    }
}
export default Notification;