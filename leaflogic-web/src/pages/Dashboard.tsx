function Dashboard() {
  return (
    <div className="page">
      <div className="glass-panel">
        <h2 className="page-title">Dashboard</h2>
        <p className="page-description">View your health and nutrition overview</p>
        
        <div className="dashboard-grid">
          <div className="stat-card">
            <h3>Daily Calories</h3>
            <div className="stat-value">1,850</div>
            <div className="stat-label">of 2,000 kcal</div>
          </div>
          
          <div className="stat-card">
            <h3>Water Intake</h3>
            <div className="stat-value">6</div>
            <div className="stat-label">of 8 glasses</div>
          </div>
          
          <div className="stat-card">
            <h3>Protein</h3>
            <div className="stat-value">75g</div>
            <div className="stat-label">of 100g goal</div>
          </div>
          
          <div className="stat-card">
            <h3>Active Minutes</h3>
            <div className="stat-value">45</div>
            <div className="stat-label">of 60 min</div>
          </div>
        </div>

        <div className="recent-meals">
          <h3>Recent Meals</h3>
          <div className="meal-list">
            <div className="meal-item">
              <span className="meal-time">Breakfast</span>
              <span className="meal-name">Oatmeal with berries</span>
              <span className="meal-calories">350 kcal</span>
            </div>
            <div className="meal-item">
              <span className="meal-time">Lunch</span>
              <span className="meal-name">Grilled chicken salad</span>
              <span className="meal-calories">450 kcal</span>
            </div>
            <div className="meal-item">
              <span className="meal-time">Snack</span>
              <span className="meal-name">Apple with almond butter</span>
              <span className="meal-calories">200 kcal</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Dashboard
