import React from 'react';
import './style.css';

const FormSignin = (props) => {
    return (
        <div className="signup">
            <h1>Sign in</h1>
            <form className="signupForm" action="signin" method="POST">
                <span className="signupName"><input className="headerInput" type="text" name="login" id="signupLogin" placeholder="Your user name..." /></span>
                <span className="signupLogin"><input className="headerInput" type="text" name="pass" id="signupPass" placeholder="Your password..." /></span>
                <span className="signupPass"><input className="signupSubmit" type="submit" value="SIGN IN" /></span>
                <span className="signupNow">Not a member? <a className="link" href="/signup"><span>Sign up now!</span></a></span>
            </form>
        </div>
    )
}

export default FormSignin;