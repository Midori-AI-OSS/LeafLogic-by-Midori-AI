function Home() {
  return (
    <div className="page">
      <div className="glass-panel">
        <h2 className="page-title">Welcome to LeafLogic</h2>
        <p className="page-description">
          Track your health and nutrition with AI-powered insights. 
          LeafLogic helps you make better food choices and maintain a healthier lifestyle.
        </p>
        <div className="feature-grid">
          <div className="feature-card">
            <div className="feature-icon">📊</div>
            <h3>Track Your Progress</h3>
            <p>Monitor your daily nutrition and health metrics</p>
          </div>
          <div className="feature-card">
            <div className="feature-icon">💬</div>
            <h3>AI Chat Assistant</h3>
            <p>Get personalized advice from our cloud-based AI</p>
          </div>
          <div className="feature-card">
            <div className="feature-icon">🥗</div>
            <h3>Food Tracking</h3>
            <p>Log your meals and discover healthier alternatives</p>
          </div>
          <div className="feature-card">
            <div className="feature-icon">📈</div>
            <h3>Insights & Analytics</h3>
            <p>Understand your eating patterns and improve over time</p>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Home
