import React from 'react';
import './App.css';
import { Breadcrumb, Layout } from 'antd';
import { SideMenu } from "./components/Navigation/SideMenu";
import { Dashboard } from "./containers/Dashboard";

const { Header, Content, Footer } = Layout;

const App = () => {

    return (
        // <Router>
        <Layout style={ { minHeight: '100vh' } }>
            <SideMenu/>
            <Layout className="site-layout">
                <Header className="site-layout-background" style={ { padding: 0 } }/>
                <Content style={ { margin: '0 16px' } }>
                    <Breadcrumb style={ { margin: '16px 0' } }>
                        <Breadcrumb.Item>User</Breadcrumb.Item>
                        <Breadcrumb.Item>Bill</Breadcrumb.Item>
                    </Breadcrumb>
                    {/*<Switch>*/ }
                    {/*    <Route path={} />*/ }
                    {/*    */ }
                    {/*</Switch>*/ }
                    <Dashboard/>
                </Content>
                <Footer style={ { textAlign: 'center' } }>Ant Design ©2018 Created by Ant UED</Footer>
            </Layout>
        </Layout>
        // </Router>
    );
};

export default App;
