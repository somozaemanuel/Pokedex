USE [master]
GO
/****** Object:  Database [PokemonTest]    Script Date: 10/6/2021 16:11:21 ******/
CREATE DATABASE [PokemonTest]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PokemonTest', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\PokemonTest.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Pokemon_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\PokemonTest_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [PokemonTest] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PokemonTest].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PokemonTest] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PokemonTest] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PokemonTest] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PokemonTest] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PokemonTest] SET ARITHABORT OFF 
GO
ALTER DATABASE [PokemonTest] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PokemonTest] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PokemonTest] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PokemonTest] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PokemonTest] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PokemonTest] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PokemonTest] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PokemonTest] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PokemonTest] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PokemonTest] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PokemonTest] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PokemonTest] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PokemonTest] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PokemonTest] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PokemonTest] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PokemonTest] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PokemonTest] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PokemonTest] SET RECOVERY FULL 
GO
ALTER DATABASE [PokemonTest] SET  MULTI_USER 
GO
ALTER DATABASE [PokemonTest] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PokemonTest] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PokemonTest] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PokemonTest] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [PokemonTest] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'PokemonTest', N'ON'
GO
ALTER DATABASE [PokemonTest] SET QUERY_STORE = OFF
GO
USE [PokemonTest]
GO
/****** Object:  Table [dbo].[Evoluciones]    Script Date: 10/6/2021 16:11:21 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Evoluciones](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[PokemonBaseId] [bigint] NOT NULL,
	[PokemonEvolucionId] [bigint] NOT NULL,
	[NivelEvolucion] [int] NOT NULL,
 CONSTRAINT [PK_Evoluciones] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Habilidades]    Script Date: 10/6/2021 16:11:21 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Habilidades](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Descripcion] [varchar](50) NULL,
	[NivelAprendizaje] [int] NOT NULL,
	[Ataque] [int] NOT NULL,
	[Defensa] [int] NOT NULL,
 CONSTRAINT [PK_Habilidades] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HabilidadesPokemon]    Script Date: 10/6/2021 16:11:21 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HabilidadesPokemon](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[PokemonId] [bigint] NOT NULL,
	[HabilidadId] [bigint] NOT NULL,
 CONSTRAINT [PK_HabilidadesPokemon] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pokemones]    Script Date: 10/6/2021 16:11:21 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pokemones](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[NivelEncuentro] [int] NULL,
 CONSTRAINT [PK_Pokemones] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tipos]    Script Date: 10/6/2021 16:11:21 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tipos](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Descripcion] [varchar](10) NULL,
 CONSTRAINT [PK_Tipos] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TiposPokemon]    Script Date: 10/6/2021 16:11:21 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TiposPokemon](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[PokemonId] [bigint] NOT NULL,
	[TipoId] [bigint] NOT NULL,
 CONSTRAINT [PK_TiposPokemon] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Evoluciones]  WITH CHECK ADD  CONSTRAINT [FK_Evoluciones_Evoluciones_PokemonEvolucionId] FOREIGN KEY([PokemonEvolucionId])
REFERENCES [dbo].[Pokemones] ([Id])
GO
ALTER TABLE [dbo].[Evoluciones] CHECK CONSTRAINT [FK_Evoluciones_Evoluciones_PokemonEvolucionId]
GO
ALTER TABLE [dbo].[Evoluciones]  WITH CHECK ADD  CONSTRAINT [FK_Evoluciones_Pokemones_PokemonBaseId] FOREIGN KEY([PokemonBaseId])
REFERENCES [dbo].[Pokemones] ([Id])
GO
ALTER TABLE [dbo].[Evoluciones] CHECK CONSTRAINT [FK_Evoluciones_Pokemones_PokemonBaseId]
GO
ALTER TABLE [dbo].[HabilidadesPokemon]  WITH CHECK ADD  CONSTRAINT [FK_HabilidadesPokemon_Habilidades] FOREIGN KEY([HabilidadId])
REFERENCES [dbo].[Habilidades] ([Id])
GO
ALTER TABLE [dbo].[HabilidadesPokemon] CHECK CONSTRAINT [FK_HabilidadesPokemon_Habilidades]
GO
ALTER TABLE [dbo].[HabilidadesPokemon]  WITH CHECK ADD  CONSTRAINT [FK_HabilidadesPokemon_Pokemon] FOREIGN KEY([PokemonId])
REFERENCES [dbo].[Pokemones] ([Id])
GO
ALTER TABLE [dbo].[HabilidadesPokemon] CHECK CONSTRAINT [FK_HabilidadesPokemon_Pokemon]
GO
ALTER TABLE [dbo].[TiposPokemon]  WITH CHECK ADD  CONSTRAINT [FK_TiposPokemon_Pokemones] FOREIGN KEY([PokemonId])
REFERENCES [dbo].[Pokemones] ([Id])
GO
ALTER TABLE [dbo].[TiposPokemon] CHECK CONSTRAINT [FK_TiposPokemon_Pokemones]
GO
ALTER TABLE [dbo].[TiposPokemon]  WITH CHECK ADD  CONSTRAINT [FK_TiposPokemon_Tipos] FOREIGN KEY([TipoId])
REFERENCES [dbo].[Tipos] ([Id])
GO
ALTER TABLE [dbo].[TiposPokemon] CHECK CONSTRAINT [FK_TiposPokemon_Tipos]
GO
USE [master]
GO
ALTER DATABASE [PokemonTest] SET  READ_WRITE 
GO
