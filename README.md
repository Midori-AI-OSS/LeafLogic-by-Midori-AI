# LeafLogic-by-Midori-AI

A health and food tracking web application that uses cloud LRMs to chat with the user and learn about how to eat better.

## Tech Stack

- **Framework**: React with TypeScript
- **Build Tool**: Vite
- **Package Manager**: Bun (fallback to npm)
- **Routing**: React Router v6
- **Styling**: Custom CSS with dark stained glass iOS theme

## Features

- 🏠 **Home**: Overview of LeafLogic features and capabilities
- 📊 **Dashboard**: Track daily nutrition, calories, water intake, and meals
- 💬 **Chat**: AI-powered assistant for personalized health and nutrition advice
- 👤 **Profile**: User information, goals, preferences, and achievements

## Design Theme

The application features a **dark stained glass Apple (iOS) look** with:
- No rounded corners (sharp, clean edges)
- Glassmorphism effects with backdrop blur
- iOS-inspired color gradients
- Translucent panels with subtle borders
- Smooth transitions and hover effects

## Development

### Setup

```bash
cd leaflogic-web

# Install dependencies (using Bun)
bun install

# Or using npm
npm install
```

### Development Server

```bash
# Using Bun
bun run dev

# Or using npm
npm run dev
```

### Build for Production

```bash
# Using Bun
bun run build

# Or using npm
npm run build
```

### Preview Production Build

```bash
# Using Bun
bun run preview

# Or using npm
npm run preview
```

## Project Structure

```
leaflogic-web/
├── src/
│   ├── components/
│   │   └── Layout.tsx       # Main layout with navigation
│   ├── pages/
│   │   ├── Home.tsx         # Home page
│   │   ├── Dashboard.tsx    # Dashboard page
│   │   ├── Chat.tsx         # AI chat page
│   │   └── Profile.tsx      # User profile page
│   ├── styles/
│   │   ├── index.css        # Base styles
│   │   └── theme.css        # Theme and component styles
│   ├── App.tsx              # Main app component with routing
│   └── main.tsx             # Application entry point
├── public/                  # Static assets
├── index.html              # HTML template
└── package.json            # Dependencies and scripts
```

## Contributing

Please refer to [AGENTS.md](AGENTS.md) for development guidelines and contributor modes.
