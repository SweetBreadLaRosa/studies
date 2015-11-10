using System;

namespace StrategyPattern
{
    class PointGaurd : IPositionPlays
    {

        public string BasketballStrategies()
        {
            return "basketball plays for a point guard";
        }
    }

    public class Center : IPositionPlays
    {
        public string BasketballStrategies()
        {
            return "basketball plays for a center";
        }
    }
    
    public interface IPositionPlays
    {
        string BasketballStrategies();
    }

    public class StartingFiveStrategy
    {
        private IPositionPlays _positionPlays;

        public StartingFiveStrategy(IPositionPlays iPositionPlays)
        {
            _positionPlays = iPositionPlays;
        }

        public void DefineStrategy()
        {
            Console.WriteLine(this._positionPlays.BasketballStrategies());
        }

        private static void Main(string[] args)
        {
            StartingFiveStrategy startingFive = new StartingFiveStrategy(new PointGaurd());
            Console.WriteLine("strategy for pg");
            startingFive.DefineStrategy();

            startingFive = new StartingFiveStrategy(new Center());
            Console.WriteLine("strategy for center");
            startingFive.DefineStrategy();

            Console.ReadKey();
        }
    }
}
