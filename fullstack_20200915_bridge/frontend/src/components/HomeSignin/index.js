import React from 'react';
import HeaderLogged from '../../components/HeaderLogged';
import HeaderLoggout from '../../components/HeaderLoggout';
import FormSignin from '../FormSignin';
import './style.css';

function HomeStyle(props) {
    const isLoggedIn = props.isLoggedIn;
    if (isLoggedIn) {
        return <HeaderLogged />;
    }
    return <HeaderLoggout />;
}

function HomeSignin(props) {
    return (
        <div className="App">
            <div className="appHeader">
                <HomeStyle isLoggedIn={true} />
            </div>

            <div className="appMain">
                <FormSignin />
            </div>

        </div>
    );
}

export default HomeSignin;