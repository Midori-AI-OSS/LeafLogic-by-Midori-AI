function Profile() {
  return (
    <div className="page">
      <div className="glass-panel">
        <h2 className="page-title">Profile</h2>
        
        <div className="profile-section">
          <div className="profile-avatar">
            <div className="avatar-placeholder">👤</div>
          </div>
          
          <div className="profile-info">
            <h3>User Profile</h3>
            <div className="info-item">
              <span className="info-label">Name:</span>
              <span className="info-value">Demo User</span>
            </div>
            <div className="info-item">
              <span className="info-label">Email:</span>
              <span className="info-value">user@leaflogic.app</span>
            </div>
            <div className="info-item">
              <span className="info-label">Member since:</span>
              <span className="info-value">October 2024</span>
            </div>
          </div>
        </div>

        <div className="profile-section">
          <h3>Goals & Preferences</h3>
          <div className="info-item">
            <span className="info-label">Daily Calorie Goal:</span>
            <span className="info-value">2,000 kcal</span>
          </div>
          <div className="info-item">
            <span className="info-label">Diet Preference:</span>
            <span className="info-value">Balanced</span>
          </div>
          <div className="info-item">
            <span className="info-label">Activity Level:</span>
            <span className="info-value">Moderate</span>
          </div>
        </div>

        <div className="profile-section">
          <h3>Achievements</h3>
          <div className="achievement-grid">
            <div className="achievement-badge">
              <div className="badge-icon">🏆</div>
              <div className="badge-name">7-Day Streak</div>
            </div>
            <div className="achievement-badge">
              <div className="badge-icon">⭐</div>
              <div className="badge-name">Goal Achiever</div>
            </div>
            <div className="achievement-badge">
              <div className="badge-icon">🥇</div>
              <div className="badge-name">Early Adopter</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Profile
