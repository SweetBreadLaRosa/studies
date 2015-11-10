using System;
using System.Threading;


public class UniformSettings
    {
        public int height { get; set; }
        public int weight { get; set; }
        public int shoes { get; set; }
    }

    public abstract class UniformHandler
    {
        //can be public but protected - 
        //this can be accessed by code in the same class or struct, or in a derived class
		// to extend - protected
        protected UniformHandler _uniformHandler;

        public abstract void CreateUniform(UniformSettings uniformSettings);

        public void SetNextHandler(UniformHandler nextPersonToGetUniform) 
        {
            _uniformHandler = nextPersonToGetUniform;
        }
    }

    public class ShirtHandler : UniformHandler
    {
        public override void CreateUniform(UniformSettings uniformSettings)
        {
            if (uniformSettings.height > 6 && uniformSettings.weight > 185 && uniformSettings.shoes > 11)
            {
                Console.WriteLine("Player is {0} ft tall so they will have a large jersey/shorts", uniformSettings.height);
            }
            else
            {

                _uniformHandler.CreateUniform(uniformSettings);
                
                //ShortsHandler shortsHandler = new ShortsHandler();
                //shortsHandler.CreateUniform(uniformSettings);
            }
        }

    }

    public class ShortsHandler : UniformHandler
    {
        public override void CreateUniform(UniformSettings uniformSettings)
        {
            if (uniformSettings.height < 5.5 && uniformSettings.weight <= 150 && uniformSettings.shoes < 9)
            {
                Console.WriteLine("Player is {0} ft tall so they will have a medium jersey/shorts", uniformSettings.height);
            }
            else
            {
/*                SmallHandler smallHandler = new SmallHandler();
                smallHandler.CreateUniform(uniformSettings);*/
                _uniformHandler.CreateUniform(uniformSettings);
            }
        }
    }

public class SmallHandler : UniformHandler
{
    public override void CreateUniform(UniformSettings uniformSettings)
    {
        if (uniformSettings.height < 3 && uniformSettings.weight <= 100 && uniformSettings.shoes < 4)
        {
            Console.WriteLine("player will get small everything!");
        }
    }



    private static void Main(string[] args)
    {
        UniformHandler shirtUniformHandler = new ShirtHandler();
        UniformHandler shortsUniformHandler = new ShortsHandler();
        UniformHandler smallUniformHandler = new SmallHandler();

        shirtUniformHandler.SetNextHandler(shortsUniformHandler);
        shortsUniformHandler.SetNextHandler(smallUniformHandler);

        shirtUniformHandler.CreateUniform(new UniformSettings
        {
            height = 9,
            weight = 190,
            shoes = 12
        });

        shirtUniformHandler.CreateUniform(new UniformSettings
        {
            height = 5,
            weight = 120,
            shoes = 5
        });

        //what it looked like before

/*                shirtUniformHandler.CreateUniform(new UniformSettings
        {
            height = 5,
            weight = 150,
            shoes = 5
        }
            );

        shirtUniformHandler.CreateUniform(new UniformSettings
        {
            height = 9,
            weight = 200,
            shoes = 12
        }
            );

        shirtUniformHandler.CreateUniform(new UniformSettings
        {
            height = 8,
            weight = 190,
            shoes = 16
        }
    );

        shirtUniformHandler.CreateUniform(new UniformSettings
        {
            height = 2,
            weight = 120,
            shoes = 5
        }*/


        Thread.Sleep(10000000);
        Console.ReadKey();

    }
}

