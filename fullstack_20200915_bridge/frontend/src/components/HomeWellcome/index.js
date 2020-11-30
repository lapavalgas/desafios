import React from 'react';
import homeLogo from '../../assets/homeLogo.svg'
import './style.css';

const HomeWellcome = (props) => {
    return (
        <div className="main">
            <div className="homeLeftLogo">
                <img src={homeLogo} />
            </div>
            <div className="homeRight">
                {/* <UserFavorites /> */}
            </div>
        </div>
    )
}

export default HomeWellcome;