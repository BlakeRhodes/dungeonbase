using dungeonbase.Models.DatabaseSettings.BooksApi.Models;
using dungeonbase.Models.Interfaces;
using dungeonbase.Repositories;
using dungeonbase.Services;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Options;

namespace dungeonbase
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            // requires using Microsoft.Extensions.Options
            services.Configure<DungeonbaseDatabaseSettings>(
                Configuration.GetSection(nameof(DungeonbaseDatabaseSettings)));

            services.AddSingleton<IDungeonbaseDatabaseSettings>(sp =>
                sp.GetRequiredService<IOptions<DungeonbaseDatabaseSettings>>().Value);
            services.AddScoped<LocationService>();
            services.AddSingleton<LocationRepository>();
            services.AddControllers();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }
    }
}
