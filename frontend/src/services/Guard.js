import React, {Component} from 'react';
import {Navigate, useLocation} from 'react-router-dom';
import ApiService from './ApiService';

export const protectedRoute = ({element: Component}) => {
    const location = useLocation();

    return ApiService.isAuthenticated() ? (Component)