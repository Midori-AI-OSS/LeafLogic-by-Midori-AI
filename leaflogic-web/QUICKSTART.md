# LeafLogic Web - Quick Start Guide

## Prerequisites
- Node.js 18+ or Bun installed
- Git

## Installation

1. Clone the repository:
```bash
git clone https://github.com/Midori-AI-OSS/LeafLogic-by-Midori-AI.git
cd LeafLogic-by-Midori-AI/leaflogic-web
```

2. Install dependencies:
```bash
# Using npm (recommended for stability)
npm install

# Or using Bun
bun install
```

## Development

Start the development server:
```bash
# Using npm
npm run dev

# Or using Bun
bun run dev
```

The app will be available at `http://localhost:5173/`

## Build for Production

```bash
# Using npm
npm run build

# Or using Bun
bun run build
```

The built files will be in the `dist/` directory.

## Preview Production Build

```bash
# Using npm
npm run preview

# Or using Bun
bun run preview
```

## Pages

- **Home** (`/`) - Welcome page with feature overview
- **Dashboard** (`/dashboard`) - Health and nutrition tracking
- **Chat** (`/chat`) - AI assistant for personalized advice
- **Profile** (`/profile`) - User information and achievements

## Tech Stack

- **React 18** with TypeScript
- **Vite** for fast development and building
- **React Router v6** for routing
- **Custom CSS** with dark stained glass iOS theme

## Theme

The app uses a **dark stained glass Apple (iOS) look** featuring:
- Pure black background
- Glassmorphism effects with backdrop blur
- iOS-inspired color gradients
- Sharp corners (no rounding)
- Translucent panels with subtle borders
- Smooth transitions and animations

## Next Steps

To connect the chat to a real LRM/LLM backend:
1. Create an API endpoint for chat interactions
2. Update `src/pages/Chat.tsx` to call your API
3. Add environment variables for API configuration
4. Implement authentication if needed

For more details, see the main [README.md](../README.md) in the repository root.
