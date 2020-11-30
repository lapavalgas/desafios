import React from 'react';
import { Route, BrowserRouter } from 'react-router-dom';
import HomeMain from './components/HomeMain';
import HomeSignin from './components/HomeSignin';
import HomeSignup from './components/HomeSignup';

const Routes = () => {
  return (
    <BrowserRouter>
      <Route path="/" component={HomeMain} exact={true} />
      <Route path="/signin" component={HomeSignin} exact={true} />
      <Route path="/signup" component={HomeSignup} exact={true} />
      <Route path="/home" component={HomeMain} exact={true} />
    </BrowserRouter>
  )
}

export default Routes;
