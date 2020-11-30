import React from 'react';
import userFavorites from '../../assets/userFavorites.svg';
import './style.css';

const HeaderUserFavorites = (props) => {
    return (
        <div className="headerFavorites"><a href="/home">
            <img src={userFavorites} alt="favorites"/>
            <p>My Stars</p>
        </a>
        </div>
    )
}

export default HeaderUserFavorites;