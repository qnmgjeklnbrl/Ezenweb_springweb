const { createProxyMiddleware } = require('http-proxy-middleware');
			module.exports = function(app) {
  				app.use(
   				 	'/api',
    					createProxyMiddleware({
     					 target: 'http://localhost:8082', // 서버 도메인
      					changeOrigin: true,
    				})
  				);
			};