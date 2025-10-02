import { useState } from 'react'

function Chat() {
  const [messages, setMessages] = useState([
    { role: 'assistant', content: 'Hello! I\'m your LeafLogic AI assistant. How can I help you with your health and nutrition today?' }
  ])
  const [input, setInput] = useState('')

  const handleSend = () => {
    if (input.trim()) {
      setMessages([...messages, { role: 'user', content: input }])
      setInput('')
      setTimeout(() => {
        setMessages(prev => [...prev, { 
          role: 'assistant', 
          content: 'This is a demo response. Connect to your cloud LRM to get personalized advice!' 
        }])
      }, 500)
    }
  }

  return (
    <div className="page">
      <div className="glass-panel chat-container">
        <h2 className="page-title">AI Chat Assistant</h2>
        
        <div className="chat-messages">
          {messages.map((message, index) => (
            <div key={index} className={`message ${message.role}`}>
              <div className="message-content">{message.content}</div>
            </div>
          ))}
        </div>

        <div className="chat-input">
          <input
            type="text"
            value={input}
            onChange={(e) => setInput(e.target.value)}
            onKeyPress={(e) => e.key === 'Enter' && handleSend()}
            placeholder="Ask me about nutrition, recipes, or healthy eating..."
            className="input-field"
          />
          <button onClick={handleSend} className="send-button">Send</button>
        </div>
      </div>
    </div>
  )
}

export default Chat
