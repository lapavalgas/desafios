import React from 'react';
import appPerson from '../../assets/appPerson.svg';
import appLogout from '../../assets/appLogout.svg';
import './style.css';

const HeaderUserLogo = (props) => {
    return (
        <div className="headerBridgeUser bridgeUser">
            <div className="headerBrisgeUserName"><p>Hello, name</p></div>
            <div className="headerBridgeUserAvatar"><img src={appPerson} alt="person"></img></div>
            <div className="headerBridgeUserLogout"><a href="/logout"><img src={appLogout} alt="person"></img></a></div>
        </div>
    )
}

export default HeaderUserLogo;